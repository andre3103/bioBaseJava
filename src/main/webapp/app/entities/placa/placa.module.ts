import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { PlacaComponent } from './placa.component';
import { PlacaDetailComponent } from './placa-detail.component';
import { PlacaUpdateComponent } from './placa-update.component';
import { PlacaDeleteDialogComponent } from './placa-delete-dialog.component';
import { placaRoute } from './placa.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(placaRoute)],
  declarations: [PlacaComponent, PlacaDetailComponent, PlacaUpdateComponent, PlacaDeleteDialogComponent],
  entryComponents: [PlacaDeleteDialogComponent],
})
export class BioBaseApplicationPlacaModule {}
