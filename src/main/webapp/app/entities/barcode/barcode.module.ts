import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { BarcodeComponent } from './barcode.component';
import { BarcodeDetailComponent } from './barcode-detail.component';
import { BarcodeUpdateComponent } from './barcode-update.component';
import { BarcodeDeleteDialogComponent } from './barcode-delete-dialog.component';
import { barcodeRoute } from './barcode.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(barcodeRoute)],
  declarations: [BarcodeComponent, BarcodeDetailComponent, BarcodeUpdateComponent, BarcodeDeleteDialogComponent],
  entryComponents: [BarcodeDeleteDialogComponent],
})
export class BioBaseApplicationBarcodeModule {}
