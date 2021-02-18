import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBarcode, Barcode } from 'app/shared/model/barcode.model';
import { BarcodeService } from './barcode.service';
import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from 'app/entities/gerencia-id/gerencia-id.service';

@Component({
  selector: 'jhi-barcode-update',
  templateUrl: './barcode-update.component.html',
})
export class BarcodeUpdateComponent implements OnInit {
  isSaving = false;
  idsamples: IGerenciaId[] = [];
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    marcador: [],
    sequencia: [],
    data: [],
    idSample: [],
  });

  constructor(
    protected barcodeService: BarcodeService,
    protected gerenciaIdService: GerenciaIdService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ barcode }) => {
      this.updateForm(barcode);

      this.gerenciaIdService
        .query({ filter: 'barcode-is-null' })
        .pipe(
          map((res: HttpResponse<IGerenciaId[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGerenciaId[]) => {
          if (!barcode.idSample || !barcode.idSample.id) {
            this.idsamples = resBody;
          } else {
            this.gerenciaIdService
              .find(barcode.idSample.id)
              .pipe(
                map((subRes: HttpResponse<IGerenciaId>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGerenciaId[]) => (this.idsamples = concatRes));
          }
        });
    });
  }

  updateForm(barcode: IBarcode): void {
    this.editForm.patchValue({
      id: barcode.id,
      marcador: barcode.marcador,
      sequencia: barcode.sequencia,
      data: barcode.data,
      idSample: barcode.idSample,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const barcode = this.createFromForm();
    if (barcode.id !== undefined) {
      this.subscribeToSaveResponse(this.barcodeService.update(barcode));
    } else {
      this.subscribeToSaveResponse(this.barcodeService.create(barcode));
    }
  }

  private createFromForm(): IBarcode {
    return {
      ...new Barcode(),
      id: this.editForm.get(['id'])!.value,
      marcador: this.editForm.get(['marcador'])!.value,
      sequencia: this.editForm.get(['sequencia'])!.value,
      data: this.editForm.get(['data'])!.value,
      idSample: this.editForm.get(['idSample'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBarcode>>): void {
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

  trackById(index: number, item: IGerenciaId): any {
    return item.id;
  }
}
