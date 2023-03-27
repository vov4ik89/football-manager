import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TeamComponent} from './team/team.component';
import {PlayerComponent} from './player/player.component';

const routes: Routes = [{
    path: 'football-manager',
    children: [
      {
        path: 'team',
        component: TeamComponent
      },
      {
        path: 'player',
        component: PlayerComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
