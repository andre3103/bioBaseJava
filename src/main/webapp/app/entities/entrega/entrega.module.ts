import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { EntregaComponent } from './entrega.component';
import { EntregaDetailComponent } from './entrega-detail.component';
import { EntregaUpdateComponent } from './entrega-update.component';
import { EntregaDeleteDialogComponent } from './entrega-delete-dialog.component';
import { entregaRoute } from './entrega.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(entregaRoute)],
  declarations: [EntregaComponent, EntregaDetailComponent, EntregaUpdateComponent, EntregaDeleteDialogComponent],
  entryComponents: [EntregaDeleteDialogComponent],
})
export class BioBaseApplicationEntregaModule {}
