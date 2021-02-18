import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVoucherInfo, VoucherInfo } from 'app/shared/model/voucher-info.model';
import { VoucherInfoService } from './voucher-info.service';

@Component({
  selector: 'jhi-voucher-info-update',
  templateUrl: './voucher-info-update.component.html',
})
export class VoucherInfoUpdateComponent implements OnInit {
  isSaving = false;
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    loginName: [],
    data: [],
  });

  constructor(protected voucherInfoService: VoucherInfoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ voucherInfo }) => {
      this.updateForm(voucherInfo);
    });
  }

  updateForm(voucherInfo: IVoucherInfo): void {
    this.editForm.patchValue({
      id: voucherInfo.id,
      loginName: voucherInfo.loginName,
      data: voucherInfo.data,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const voucherInfo = this.createFromForm();
    if (voucherInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.voucherInfoService.update(voucherInfo));
    } else {
      this.subscribeToSaveResponse(this.voucherInfoService.create(voucherInfo));
    }
  }

  private createFromForm(): IVoucherInfo {
    return {
      ...new VoucherInfo(),
      id: this.editForm.get(['id'])!.value,
      loginName: this.editForm.get(['loginName'])!.value,
      data: this.editForm.get(['data'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVoucherInfo>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
