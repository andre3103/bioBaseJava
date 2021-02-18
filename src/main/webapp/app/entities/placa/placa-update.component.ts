import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPlaca, Placa } from 'app/shared/model/placa.model';
import { PlacaService } from './placa.service';

@Component({
  selector: 'jhi-placa-update',
  templateUrl: './placa-update.component.html',
})
export class PlacaUpdateComponent implements OnInit {
  isSaving = false;
  dataMontagemDp: any;
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    dataMontagem: [],
    marcador: [],
    sequencia: [],
    data: [],
    status: [null, [Validators.max(1)]],
  });

  constructor(protected placaService: PlacaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ placa }) => {
      this.updateForm(placa);
    });
  }

  updateForm(placa: IPlaca): void {
    this.editForm.patchValue({
      id: placa.id,
      dataMontagem: placa.dataMontagem,
      marcador: placa.marcador,
      sequencia: placa.sequencia,
      data: placa.data,
      status: placa.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const placa = this.createFromForm();
    if (placa.id !== undefined) {
      this.subscribeToSaveResponse(this.placaService.update(placa));
    } else {
      this.subscribeToSaveResponse(this.placaService.create(placa));
    }
  }

  private createFromForm(): IPlaca {
    return {
      ...new Placa(),
      id: this.editForm.get(['id'])!.value,
      dataMontagem: this.editForm.get(['dataMontagem'])!.value,
      marcador: this.editForm.get(['marcador'])!.value,
      sequencia: this.editForm.get(['sequencia'])!.value,
      data: this.editForm.get(['data'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlaca>>): void {
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
