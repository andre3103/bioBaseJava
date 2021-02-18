import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { RecebimentoComponent } from './recebimento.component';
import { RecebimentoDetailComponent } from './recebimento-detail.component';
import { RecebimentoUpdateComponent } from './recebimento-update.component';
import { RecebimentoDeleteDialogComponent } from './recebimento-delete-dialog.component';
import { recebimentoRoute } from './recebimento.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(recebimentoRoute)],
  declarations: [RecebimentoComponent, RecebimentoDetailComponent, RecebimentoUpdateComponent, RecebimentoDeleteDialogComponent],
  entryComponents: [RecebimentoDeleteDialogComponent],
})
export class BioBaseApplicationRecebimentoModule {}
