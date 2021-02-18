import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { VoucherInfoComponent } from './voucher-info.component';
import { VoucherInfoDetailComponent } from './voucher-info-detail.component';
import { VoucherInfoUpdateComponent } from './voucher-info-update.component';
import { VoucherInfoDeleteDialogComponent } from './voucher-info-delete-dialog.component';
import { voucherInfoRoute } from './voucher-info.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(voucherInfoRoute)],
  declarations: [VoucherInfoComponent, VoucherInfoDetailComponent, VoucherInfoUpdateComponent, VoucherInfoDeleteDialogComponent],
  entryComponents: [VoucherInfoDeleteDialogComponent],
})
export class BioBaseApplicationVoucherInfoModule {}
