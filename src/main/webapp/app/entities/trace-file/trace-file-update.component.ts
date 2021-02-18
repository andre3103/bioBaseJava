import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITraceFile, TraceFile } from 'app/shared/model/trace-file.model';
import { TraceFileService } from './trace-file.service';
import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from 'app/entities/gerencia-id/gerencia-id.service';
import { ISequenciamento } from 'app/shared/model/sequenciamento.model';
import { SequenciamentoService } from 'app/entities/sequenciamento/sequenciamento.service';

type SelectableEntity = IGerenciaId | ISequenciamento;

@Component({
  selector: 'jhi-trace-file-update',
  templateUrl: './trace-file-update.component.html',
})
export class TraceFileUpdateComponent implements OnInit {
  isSaving = false;
  idsamples: IGerenciaId[] = [];
  idsequenciamentos: ISequenciamento[] = [];

  editForm = this.fb.group({
    id: [],
    dirPhd: [],
    dirAb: [],
    idSample: [],
    idSequenciamento: [],
  });

  constructor(
    protected traceFileService: TraceFileService,
    protected gerenciaIdService: GerenciaIdService,
    protected sequenciamentoService: SequenciamentoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ traceFile }) => {
      this.updateForm(traceFile);

      this.gerenciaIdService
        .query({ filter: 'tracefile-is-null' })
        .pipe(
          map((res: HttpResponse<IGerenciaId[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGerenciaId[]) => {
          if (!traceFile.idSample || !traceFile.idSample.id) {
            this.idsamples = resBody;
          } else {
            this.gerenciaIdService
              .find(traceFile.idSample.id)
              .pipe(
                map((subRes: HttpResponse<IGerenciaId>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGerenciaId[]) => (this.idsamples = concatRes));
          }
        });

      this.sequenciamentoService
        .query({ filter: 'tracefile-is-null' })
        .pipe(
          map((res: HttpResponse<ISequenciamento[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISequenciamento[]) => {
          if (!traceFile.idSequenciamento || !traceFile.idSequenciamento.id) {
            this.idsequenciamentos = resBody;
          } else {
            this.sequenciamentoService
              .find(traceFile.idSequenciamento.id)
              .pipe(
                map((subRes: HttpResponse<ISequenciamento>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISequenciamento[]) => (this.idsequenciamentos = concatRes));
          }
        });
    });
  }

  updateForm(traceFile: ITraceFile): void {
    this.editForm.patchValue({
      id: traceFile.id,
      dirPhd: traceFile.dirPhd,
      dirAb: traceFile.dirAb,
      idSample: traceFile.idSample,
      idSequenciamento: traceFile.idSequenciamento,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const traceFile = this.createFromForm();
    if (traceFile.id !== undefined) {
      this.subscribeToSaveResponse(this.traceFileService.update(traceFile));
    } else {
      this.subscribeToSaveResponse(this.traceFileService.create(traceFile));
    }
  }

  private createFromForm(): ITraceFile {
    return {
      ...new TraceFile(),
      id: this.editForm.get(['id'])!.value,
      dirPhd: this.editForm.get(['dirPhd'])!.value,
      dirAb: this.editForm.get(['dirAb'])!.value,
      idSample: this.editForm.get(['idSample'])!.value,
      idSequenciamento: this.editForm.get(['idSequenciamento'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITraceFile>>): void {
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
