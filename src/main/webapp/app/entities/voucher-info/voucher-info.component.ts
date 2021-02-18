import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVoucherInfo } from 'app/shared/model/voucher-info.model';
import { VoucherInfoService } from './voucher-info.service';
import { VoucherInfoDeleteDialogComponent } from './voucher-info-delete-dialog.component';

@Component({
  selector: 'jhi-voucher-info',
  templateUrl: './voucher-info.component.html',
})
export class VoucherInfoComponent implements OnInit, OnDestroy {
  voucherInfos?: IVoucherInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected voucherInfoService: VoucherInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.voucherInfoService.query().subscribe((res: HttpResponse<IVoucherInfo[]>) => (this.voucherInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVoucherInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVoucherInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVoucherInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('voucherInfoListModification', () => this.loadAll());
  }

  delete(voucherInfo: IVoucherInfo): void {
    const modalRef = this.modalService.open(VoucherInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.voucherInfo = voucherInfo;
  }
}
