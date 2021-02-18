import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVoucherInfo } from 'app/shared/model/voucher-info.model';

@Component({
  selector: 'jhi-voucher-info-detail',
  templateUrl: './voucher-info-detail.component.html',
})
export class VoucherInfoDetailComponent implements OnInit {
  voucherInfo: IVoucherInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ voucherInfo }) => (this.voucherInfo = voucherInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
