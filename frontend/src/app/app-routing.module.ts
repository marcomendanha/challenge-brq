import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuarioListComponent } from './usuario/usuario-list/usuario-list.component';

const routes: Routes = [
  { path: 'usuario', component: UsuarioComponent },
  { path: 'usuario-list', component: UsuarioListComponent },
  { path: '', redirectTo: '/', pathMatch: 'full' }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
