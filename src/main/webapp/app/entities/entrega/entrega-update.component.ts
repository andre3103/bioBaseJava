import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntrega, Entrega } from 'app/shared/model/entrega.model';
import { EntregaService } from './entrega.service';

@Component({
  selector: 'jhi-entrega-update',
  templateUrl: './entrega-update.component.html',
})
export class EntregaUpdateComponent implements OnInit {
  isSaving = false;
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    loginName: [],
    data: [],
  });

  constructor(protected entregaService: EntregaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entrega }) => {
      this.updateForm(entrega);
    });
  }

  updateForm(entrega: IEntrega): void {
    this.editForm.patchValue({
      id: entrega.id,
      loginName: entrega.loginName,
      data: entrega.data,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entrega = this.createFromForm();
    if (entrega.id !== undefined) {
      this.subscribeToSaveResponse(this.entregaService.update(entrega));
    } else {
      this.subscribeToSaveResponse(this.entregaService.create(entrega));
    }
  }

  private createFromForm(): IEntrega {
    return {
      ...new Entrega(),
      id: this.editForm.get(['id'])!.value,
      loginName: this.editForm.get(['loginName'])!.value,
      data: this.editForm.get(['data'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntrega>>): void {
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
