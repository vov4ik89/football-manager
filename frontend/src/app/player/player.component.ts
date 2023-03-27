import { Component } from '@angular/core';
import {Player} from '../model/player';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Location} from '@angular/common';
import {GlobalConstants} from '../model/globalConstants';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent {
  private url = GlobalConstants.apiURL + '/players';
  players: Array<Player> = [];

  reactiveForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    age: new FormControl(0),
    experience: new FormControl(0),
    teamId: new FormControl(0),
  })

  constructor(
    private http: HttpClient,
    private location: Location,
    private formBuilder: FormBuilder
  ) {}

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  save() {
    const firstName = this.reactiveForm.controls.firstName.value;
    const lastName = this.reactiveForm.controls.lastName.value;
    const age = this.reactiveForm.controls.age.value;
    const experience = this.reactiveForm.controls.experience.value;
    const teamId = this.reactiveForm.controls.teamId.value;
    if(!firstName || !lastName || !age
      || !experience || !teamId) {
      return;
    }
    this.createPlayer({firstName, lastName, age, experience, teamId} as Player)
      .subscribe(player => {this.players.push(player);});
  }

  private createPlayer(player: Player): Observable<Player> {
    return this.http.post<Player>(this.url, player, this.httpOptions);
  }

  private updatePlayer(player: Player): Observable<Player> {
    return this.http.put<Player>(this.url+'/'+player.id, player, this.httpOptions);
  }
}
