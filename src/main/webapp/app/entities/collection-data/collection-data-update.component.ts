import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICollectionData, CollectionData } from 'app/shared/model/collection-data.model';
import { CollectionDataService } from './collection-data.service';
import { IVoucherInfo } from 'app/shared/model/voucher-info.model';
import { VoucherInfoService } from 'app/entities/voucher-info/voucher-info.service';

@Component({
  selector: 'jhi-collection-data-update',
  templateUrl: './collection-data-update.component.html',
})
export class CollectionDataUpdateComponent implements OnInit {
  isSaving = false;
  voucherinfos: IVoucherInfo[] = [];
  collectionDateDp: any;

  editForm = this.fb.group({
    id: [],
    collector: [],
    collectionDate: [],
    countryOcean: [],
    stateProvince: [],
    region: [],
    sector: [],
    exactSite: [],
    identif: [],
    latitude: [],
    longitude: [],
    elevation: [],
    depth: [],
    elevationPrecision: [],
    depthPrecision: [],
    gpsSource: [],
    coordinateAccuracy: [],
    eventTime: [],
    collectionDateOccuracy: [],
    habitat: [],
    samplingProtocol: [],
    collectionNotes: [],
    siteCode: [],
    collectionEventId: [],
    voucherInfo: [],
  });

  constructor(
    protected collectionDataService: CollectionDataService,
    protected voucherInfoService: VoucherInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ collectionData }) => {
      this.updateForm(collectionData);

      this.voucherInfoService
        .query({ filter: 'collectiondata-is-null' })
        .pipe(
          map((res: HttpResponse<IVoucherInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVoucherInfo[]) => {
          if (!collectionData.voucherInfo || !collectionData.voucherInfo.id) {
            this.voucherinfos = resBody;
          } else {
            this.voucherInfoService
              .find(collectionData.voucherInfo.id)
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

  updateForm(collectionData: ICollectionData): void {
    this.editForm.patchValue({
      id: collectionData.id,
      collector: collectionData.collector,
      collectionDate: collectionData.collectionDate,
      countryOcean: collectionData.countryOcean,
      stateProvince: collectionData.stateProvince,
      region: collectionData.region,
      sector: collectionData.sector,
      exactSite: collectionData.exactSite,
      identif: collectionData.identif,
      latitude: collectionData.latitude,
      longitude: collectionData.longitude,
      elevation: collectionData.elevation,
      depth: collectionData.depth,
      elevationPrecision: collectionData.elevationPrecision,
      depthPrecision: collectionData.depthPrecision,
      gpsSource: collectionData.gpsSource,
      coordinateAccuracy: collectionData.coordinateAccuracy,
      eventTime: collectionData.eventTime,
      collectionDateOccuracy: collectionData.collectionDateOccuracy,
      habitat: collectionData.habitat,
      samplingProtocol: collectionData.samplingProtocol,
      collectionNotes: collectionData.collectionNotes,
      siteCode: collectionData.siteCode,
      collectionEventId: collectionData.collectionEventId,
      voucherInfo: collectionData.voucherInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const collectionData = this.createFromForm();
    if (collectionData.id !== undefined) {
      this.subscribeToSaveResponse(this.collectionDataService.update(collectionData));
    } else {
      this.subscribeToSaveResponse(this.collectionDataService.create(collectionData));
    }
  }

  private createFromForm(): ICollectionData {
    return {
      ...new CollectionData(),
      id: this.editForm.get(['id'])!.value,
      collector: this.editForm.get(['collector'])!.value,
      collectionDate: this.editForm.get(['collectionDate'])!.value,
      countryOcean: this.editForm.get(['countryOcean'])!.value,
      stateProvince: this.editForm.get(['stateProvince'])!.value,
      region: this.editForm.get(['region'])!.value,
      sector: this.editForm.get(['sector'])!.value,
      exactSite: this.editForm.get(['exactSite'])!.value,
      identif: this.editForm.get(['identif'])!.value,
      latitude: this.editForm.get(['latitude'])!.value,
      longitude: this.editForm.get(['longitude'])!.value,
      elevation: this.editForm.get(['elevation'])!.value,
      depth: this.editForm.get(['depth'])!.value,
      elevationPrecision: this.editForm.get(['elevationPrecision'])!.value,
      depthPrecision: this.editForm.get(['depthPrecision'])!.value,
      gpsSource: this.editForm.get(['gpsSource'])!.value,
      coordinateAccuracy: this.editForm.get(['coordinateAccuracy'])!.value,
      eventTime: this.editForm.get(['eventTime'])!.value,
      collectionDateOccuracy: this.editForm.get(['collectionDateOccuracy'])!.value,
      habitat: this.editForm.get(['habitat'])!.value,
      samplingProtocol: this.editForm.get(['samplingProtocol'])!.value,
      collectionNotes: this.editForm.get(['collectionNotes'])!.value,
      siteCode: this.editForm.get(['siteCode'])!.value,
      collectionEventId: this.editForm.get(['collectionEventId'])!.value,
      voucherInfo: this.editForm.get(['voucherInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICollectionData>>): void {
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
