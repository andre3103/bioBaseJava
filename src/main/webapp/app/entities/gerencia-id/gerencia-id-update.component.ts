import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGerenciaId, GerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from './gerencia-id.service';
import { IVoucherInfo } from 'app/shared/model/voucher-info.model';
import { VoucherInfoService } from 'app/entities/voucher-info/voucher-info.service';
import { IEntrega } from 'app/shared/model/entrega.model';
import { EntregaService } from 'app/entities/entrega/entrega.service';
import { IRecebimento } from 'app/shared/model/recebimento.model';
import { RecebimentoService } from 'app/entities/recebimento/recebimento.service';

type SelectableEntity = IVoucherInfo | IEntrega | IRecebimento;

@Component({
  selector: 'jhi-gerencia-id-update',
  templateUrl: './gerencia-id-update.component.html',
})
export class GerenciaIdUpdateComponent implements OnInit {
  isSaving = false;
  voucherinfos: IVoucherInfo[] = [];
  entregas: IEntrega[] = [];
  recebimentos: IRecebimento[] = [];

  editForm = this.fb.group({
    id: [],
    loginName: [],
    dataGeracao: [],
    status: [],
    voucherInfo: [],
    entrega: [],
    recebimento: [],
  });

  constructor(
    protected gerenciaIdService: GerenciaIdService,
    protected voucherInfoService: VoucherInfoService,
    protected entregaService: EntregaService,
    protected recebimentoService: RecebimentoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gerenciaId }) => {
      if (!gerenciaId.id) {
        const today = moment().startOf('day');
        gerenciaId.dataGeracao = today;
      }

      this.updateForm(gerenciaId);

      this.voucherInfoService
        .query({ filter: 'gerenciaid-is-null' })
        .pipe(
          map((res: HttpResponse<IVoucherInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVoucherInfo[]) => {
          if (!gerenciaId.voucherInfo || !gerenciaId.voucherInfo.id) {
            this.voucherinfos = resBody;
          } else {
            this.voucherInfoService
              .find(gerenciaId.voucherInfo.id)
              .pipe(
                map((subRes: HttpResponse<IVoucherInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IVoucherInfo[]) => (this.voucherinfos = concatRes));
          }
        });

      this.entregaService.query().subscribe((res: HttpResponse<IEntrega[]>) => (this.entregas = res.body || []));

      this.recebimentoService.query().subscribe((res: HttpResponse<IRecebimento[]>) => (this.recebimentos = res.body || []));
    });
  }

  updateForm(gerenciaId: IGerenciaId): void {
    this.editForm.patchValue({
      id: gerenciaId.id,
      loginName: gerenciaId.loginName,
      dataGeracao: gerenciaId.dataGeracao ? gerenciaId.dataGeracao.format(DATE_TIME_FORMAT) : null,
      status: gerenciaId.status,
      voucherInfo: gerenciaId.voucherInfo,
      entrega: gerenciaId.entrega,
      recebimento: gerenciaId.recebimento,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gerenciaId = this.createFromForm();
    if (gerenciaId.id !== undefined) {
      this.subscribeToSaveResponse(this.gerenciaIdService.update(gerenciaId));
    } else {
      this.subscribeToSaveResponse(this.gerenciaIdService.create(gerenciaId));
    }
  }

  private createFromForm(): IGerenciaId {
    return {
      ...new GerenciaId(),
      id: this.editForm.get(['id'])!.value,
      loginName: this.editForm.get(['loginName'])!.value,
      dataGeracao: this.editForm.get(['dataGeracao'])!.value
        ? moment(this.editForm.get(['dataGeracao'])!.value, DATE_TIME_FORMAT)
        : undefined,
      status: this.editForm.get(['status'])!.value,
      voucherInfo: this.editForm.get(['voucherInfo'])!.value,
      entrega: this.editForm.get(['entrega'])!.value,
      recebimento: this.editForm.get(['recebimento'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGerenciaId>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
