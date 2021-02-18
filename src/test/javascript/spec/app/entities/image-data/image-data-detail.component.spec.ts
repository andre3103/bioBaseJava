import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { ImageDataDetailComponent } from 'app/entities/image-data/image-data-detail.component';
import { ImageData } from 'app/shared/model/image-data.model';

describe('Component Tests', () => {
  describe('ImageData Management Detail Component', () => {
    let comp: ImageDataDetailComponent;
    let fixture: ComponentFixture<ImageDataDetailComponent>;
    const route = ({ data: of({ imageData: new ImageData(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [ImageDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ImageDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ImageDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load imageData on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.imageData).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
