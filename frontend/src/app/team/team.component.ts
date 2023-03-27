import { Component } from '@angular/core';
import {Observable} from 'rxjs';
import {Player} from '../model/player';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Team} from '../model/team';
import {GlobalConstants} from '../model/globalConstants';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-teams',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent {
  private url = GlobalConstants.apiURL + '/teams';
  teams: Array<Team>=[]
  players: Array<Player>=[]

  teamForm = new FormGroup({
    name: new FormControl(''),
  })

  teamIdForm = new FormGroup({
    id: new FormControl(0),
  })

  transferForm = new FormGroup({
    teamId: new FormControl(0),
    playerId: new FormControl(0),
  })

  constructor(private http: HttpClient){}

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' ,
    'Access-Control-Allow-Origin': '*'})
  };

  toNumber(value: string) {
    return Number(value);
  }

  save() {
    let name = this.teamForm.controls.name.value;
    if (!name) {
      return;
    }
    name = name.trim();
    this.createTeam({name} as Team)
      .subscribe(team => this.teams.push(team));
  }

  private createTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(this.url, team, this.httpOptions).pipe();
  }

  transfer() {
    const teamId = this.transferForm.controls.teamId.value;
    const playerId = this.transferForm.controls.playerId.value;
    if (!teamId) {return;}
    this.http.get<number>(this.url+'/'+teamId+'/'+playerId+'/transfer')
  }
}
