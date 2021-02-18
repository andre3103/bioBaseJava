import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { GerenciaIdComponent } from './gerencia-id.component';
import { GerenciaIdDetailComponent } from './gerencia-id-detail.component';
import { GerenciaIdUpdateComponent } from './gerencia-id-update.component';
import { GerenciaIdDeleteDialogComponent } from './gerencia-id-delete-dialog.component';
import { gerenciaIdRoute } from './gerencia-id.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(gerenciaIdRoute)],
  declarations: [GerenciaIdComponent, GerenciaIdDetailComponent, GerenciaIdUpdateComponent, GerenciaIdDeleteDialogComponent],
  entryComponents: [GerenciaIdDeleteDialogComponent],
})
export class BioBaseApplicationGerenciaIdModule {}
