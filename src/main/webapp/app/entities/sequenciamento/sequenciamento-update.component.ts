import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISequenciamento, Sequenciamento } from 'app/shared/model/sequenciamento.model';
import { SequenciamentoService } from './sequenciamento.service';
import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from 'app/entities/gerencia-id/gerencia-id.service';
import { IPlaca } from 'app/shared/model/placa.model';
import { PlacaService } from 'app/entities/placa/placa.service';

type SelectableEntity = IGerenciaId | IPlaca;

@Component({
  selector: 'jhi-sequenciamento-update',
  templateUrl: './sequenciamento-update.component.html',
})
export class SequenciamentoUpdateComponent implements OnInit {
  isSaving = false;
  idsamples: IGerenciaId[] = [];
  idplacas: IPlaca[] = [];

  editForm = this.fb.group({
    id: [],
    posiao: [],
    status: [],
    idSample: [],
    idPlaca: [],
  });

  constructor(
    protected sequenciamentoService: SequenciamentoService,
    protected gerenciaIdService: GerenciaIdService,
    protected placaService: PlacaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sequenciamento }) => {
      this.updateForm(sequenciamento);

      this.gerenciaIdService
        .query({ filter: 'sequenciamento-is-null' })
        .pipe(
          map((res: HttpResponse<IGerenciaId[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGerenciaId[]) => {
          if (!sequenciamento.idSample || !sequenciamento.idSample.id) {
            this.idsamples = resBody;
          } else {
            this.gerenciaIdService
              .find(sequenciamento.idSample.id)
              .pipe(
                map((subRes: HttpResponse<IGerenciaId>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGerenciaId[]) => (this.idsamples = concatRes));
          }
        });

      this.placaService
        .query({ filter: 'sequenciamento-is-null' })
        .pipe(
          map((res: HttpResponse<IPlaca[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPlaca[]) => {
          if (!sequenciamento.idPlaca || !sequenciamento.idPlaca.id) {
            this.idplacas = resBody;
          } else {
            this.placaService
              .find(sequenciamento.idPlaca.id)
              .pipe(
                map((subRes: HttpResponse<IPlaca>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPlaca[]) => (this.idplacas = concatRes));
          }
        });
    });
  }

  updateForm(sequenciamento: ISequenciamento): void {
    this.editForm.patchValue({
      id: sequenciamento.id,
      posiao: sequenciamento.posiao,
      status: sequenciamento.status,
      idSample: sequenciamento.idSample,
      idPlaca: sequenciamento.idPlaca,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sequenciamento = this.createFromForm();
    if (sequenciamento.id !== undefined) {
      this.subscribeToSaveResponse(this.sequenciamentoService.update(sequenciamento));
    } else {
      this.subscribeToSaveResponse(this.sequenciamentoService.create(sequenciamento));
    }
  }

  private createFromForm(): ISequenciamento {
    return {
      ...new Sequenciamento(),
      id: this.editForm.get(['id'])!.value,
      posiao: this.editForm.get(['posiao'])!.value,
      status: this.editForm.get(['status'])!.value,
      idSample: this.editForm.get(['idSample'])!.value,
      idPlaca: this.editForm.get(['idPlaca'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISequenciamento>>): void {
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
