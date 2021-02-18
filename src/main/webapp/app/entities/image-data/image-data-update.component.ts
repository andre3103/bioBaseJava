import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IImageData, ImageData } from 'app/shared/model/image-data.model';
import { ImageDataService } from './image-data.service';
import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from 'app/entities/gerencia-id/gerencia-id.service';

@Component({
  selector: 'jhi-image-data-update',
  templateUrl: './image-data-update.component.html',
})
export class ImageDataUpdateComponent implements OnInit {
  isSaving = false;
  idsamples: IGerenciaId[] = [];

  editForm = this.fb.group({
    id: [],
    nameImage: [],
    imageFile: [],
    originalSpecimen: [],
    viewMetadata: [],
    caption: [],
    measurement: [],
    measurementType: [],
    processId: [],
    licenseHolder: [],
    license: [],
    licenseYear: [],
    licenseInstitution: [],
    licenseContact: [],
    photographer: [],
    idSample: [],
  });

  constructor(
    protected imageDataService: ImageDataService,
    protected gerenciaIdService: GerenciaIdService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ imageData }) => {
      this.updateForm(imageData);

      this.gerenciaIdService
        .query({ filter: 'imagedata-is-null' })
        .pipe(
          map((res: HttpResponse<IGerenciaId[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGerenciaId[]) => {
          if (!imageData.idSample || !imageData.idSample.id) {
            this.idsamples = resBody;
          } else {
            this.gerenciaIdService
              .find(imageData.idSample.id)
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

  updateForm(imageData: IImageData): void {
    this.editForm.patchValue({
      id: imageData.id,
      nameImage: imageData.nameImage,
      imageFile: imageData.imageFile,
      originalSpecimen: imageData.originalSpecimen,
      viewMetadata: imageData.viewMetadata,
      caption: imageData.caption,
      measurement: imageData.measurement,
      measurementType: imageData.measurementType,
      processId: imageData.processId,
      licenseHolder: imageData.licenseHolder,
      license: imageData.license,
      licenseYear: imageData.licenseYear,
      licenseInstitution: imageData.licenseInstitution,
      licenseContact: imageData.licenseContact,
      photographer: imageData.photographer,
      idSample: imageData.idSample,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const imageData = this.createFromForm();
    if (imageData.id !== undefined) {
      this.subscribeToSaveResponse(this.imageDataService.update(imageData));
    } else {
      this.subscribeToSaveResponse(this.imageDataService.create(imageData));
    }
  }

  private createFromForm(): IImageData {
    return {
      ...new ImageData(),
      id: this.editForm.get(['id'])!.value,
      nameImage: this.editForm.get(['nameImage'])!.value,
      imageFile: this.editForm.get(['imageFile'])!.value,
      originalSpecimen: this.editForm.get(['originalSpecimen'])!.value,
      viewMetadata: this.editForm.get(['viewMetadata'])!.value,
      caption: this.editForm.get(['caption'])!.value,
      measurement: this.editForm.get(['measurement'])!.value,
      measurementType: this.editForm.get(['measurementType'])!.value,
      processId: this.editForm.get(['processId'])!.value,
      licenseHolder: this.editForm.get(['licenseHolder'])!.value,
      license: this.editForm.get(['license'])!.value,
      licenseYear: this.editForm.get(['licenseYear'])!.value,
      licenseInstitution: this.editForm.get(['licenseInstitution'])!.value,
      licenseContact: this.editForm.get(['licenseContact'])!.value,
      photographer: this.editForm.get(['photographer'])!.value,
      idSample: this.editForm.get(['idSample'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageData>>): void {
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
