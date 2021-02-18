import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImageData } from 'app/shared/model/image-data.model';

@Component({
  selector: 'jhi-image-data-detail',
  templateUrl: './image-data-detail.component.html',
})
export class ImageDataDetailComponent implements OnInit {
  imageData: IImageData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ imageData }) => (this.imageData = imageData));
  }

  previousState(): void {
    window.history.back();
  }
}
