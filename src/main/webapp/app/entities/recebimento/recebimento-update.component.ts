import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRecebimento, Recebimento } from 'app/shared/model/recebimento.model';
import { RecebimentoService } from './recebimento.service';

@Component({
  selector: 'jhi-recebimento-update',
  templateUrl: './recebimento-update.component.html',
})
export class RecebimentoUpdateComponent implements OnInit {
  isSaving = false;
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    loginName: [],
    data: [],
  });

  constructor(protected recebimentoService: RecebimentoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ recebimento }) => {
      this.updateForm(recebimento);
    });
  }

  updateForm(recebimento: IRecebimento): void {
    this.editForm.patchValue({
      id: recebimento.id,
      loginName: recebimento.loginName,
      data: recebimento.data,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const recebimento = this.createFromForm();
    if (recebimento.id !== undefined) {
      this.subscribeToSaveResponse(this.recebimentoService.update(recebimento));
    } else {
      this.subscribeToSaveResponse(this.recebimentoService.create(recebimento));
    }
  }

  private createFromForm(): IRecebimento {
    return {
      ...new Recebimento(),
      id: this.editForm.get(['id'])!.value,
      loginName: this.editForm.get(['loginName'])!.value,
      data: this.editForm.get(['data'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecebimento>>): void {
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
