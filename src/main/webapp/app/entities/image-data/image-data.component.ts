import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IImageData } from 'app/shared/model/image-data.model';
import { ImageDataService } from './image-data.service';
import { ImageDataDeleteDialogComponent } from './image-data-delete-dialog.component';

@Component({
  selector: 'jhi-image-data',
  templateUrl: './image-data.component.html',
})
export class ImageDataComponent implements OnInit, OnDestroy {
  imageData?: IImageData[];
  eventSubscriber?: Subscription;

  constructor(protected imageDataService: ImageDataService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.imageDataService.query().subscribe((res: HttpResponse<IImageData[]>) => (this.imageData = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInImageData();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IImageData): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInImageData(): void {
    this.eventSubscriber = this.eventManager.subscribe('imageDataListModification', () => this.loadAll());
  }

  delete(imageData: IImageData): void {
    const modalRef = this.modalService.open(ImageDataDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.imageData = imageData;
  }
}
