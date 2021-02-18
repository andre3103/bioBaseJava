import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { SequenciamentoComponent } from './sequenciamento.component';
import { SequenciamentoDetailComponent } from './sequenciamento-detail.component';
import { SequenciamentoUpdateComponent } from './sequenciamento-update.component';
import { SequenciamentoDeleteDialogComponent } from './sequenciamento-delete-dialog.component';
import { sequenciamentoRoute } from './sequenciamento.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(sequenciamentoRoute)],
  declarations: [
    SequenciamentoComponent,
    SequenciamentoDetailComponent,
    SequenciamentoUpdateComponent,
    SequenciamentoDeleteDialogComponent,
  ],
  entryComponents: [SequenciamentoDeleteDialogComponent],
})
export class BioBaseApplicationSequenciamentoModule {}
