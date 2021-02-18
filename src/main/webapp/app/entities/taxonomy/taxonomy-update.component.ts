import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITaxonomy, Taxonomy } from 'app/shared/model/taxonomy.model';
import { TaxonomyService } from './taxonomy.service';
import { IVoucherInfo } from 'app/shared/model/voucher-info.model';
import { VoucherInfoService } from 'app/entities/voucher-info/voucher-info.service';

@Component({
  selector: 'jhi-taxonomy-update',
  templateUrl: './taxonomy-update.component.html',
})
export class TaxonomyUpdateComponent implements OnInit {
  isSaving = false;
  voucherinfos: IVoucherInfo[] = [];

  editForm = this.fb.group({
    id: [],
    phylum: [],
    tclass: [],
    torder: [],
    family: [],
    subfamily: [],
    genus: [],
    specie: [],
    identif: [],
    identifierEmail: [],
    identifierInstitution: [],
    identificationMethod: [],
    taxonomyNotes: [],
    voucherInfo: [],
  });

  constructor(
    protected taxonomyService: TaxonomyService,
    protected voucherInfoService: VoucherInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taxonomy }) => {
      this.updateForm(taxonomy);

      this.voucherInfoService
        .query({ filter: 'taxonomy-is-null' })
        .pipe(
          map((res: HttpResponse<IVoucherInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVoucherInfo[]) => {
          if (!taxonomy.voucherInfo || !taxonomy.voucherInfo.id) {
            this.voucherinfos = resBody;
          } else {
            this.voucherInfoService
              .find(taxonomy.voucherInfo.id)
              .pipe(
                map((subRes: HttpResponse<IVoucherInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IVoucherInfo[]) => (this.voucherinfos = concatRes));
          }
        });
    });
  }

  updateForm(taxonomy: ITaxonomy): void {
    this.editForm.patchValue({
      id: taxonomy.id,
      phylum: taxonomy.phylum,
      tclass: taxonomy.tclass,
      torder: taxonomy.torder,
      family: taxonomy.family,
      subfamily: taxonomy.subfamily,
      genus: taxonomy.genus,
      specie: taxonomy.specie,
      identif: taxonomy.identif,
      identifierEmail: taxonomy.identifierEmail,
      identifierInstitution: taxonomy.identifierInstitution,
      identificationMethod: taxonomy.identificationMethod,
      taxonomyNotes: taxonomy.taxonomyNotes,
      voucherInfo: taxonomy.voucherInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const taxonomy = this.createFromForm();
    if (taxonomy.id !== undefined) {
      this.subscribeToSaveResponse(this.taxonomyService.update(taxonomy));
    } else {
      this.subscribeToSaveResponse(this.taxonomyService.create(taxonomy));
    }
  }

  private createFromForm(): ITaxonomy {
    return {
      ...new Taxonomy(),
      id: this.editForm.get(['id'])!.value,
      phylum: this.editForm.get(['phylum'])!.value,
      tclass: this.editForm.get(['tclass'])!.value,
      torder: this.editForm.get(['torder'])!.value,
      family: this.editForm.get(['family'])!.value,
      subfamily: this.editForm.get(['subfamily'])!.value,
      genus: this.editForm.get(['genus'])!.value,
      specie: this.editForm.get(['specie'])!.value,
      identif: this.editForm.get(['identif'])!.value,
      identifierEmail: this.editForm.get(['identifierEmail'])!.value,
      identifierInstitution: this.editForm.get(['identifierInstitution'])!.value,
      identificationMethod: this.editForm.get(['identificationMethod'])!.value,
      taxonomyNotes: this.editForm.get(['taxonomyNotes'])!.value,
      voucherInfo: this.editForm.get(['voucherInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaxonomy>>): void {
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

  trackById(index: number, item: IVoucherInfo): any {
    return item.id;
  }
}
