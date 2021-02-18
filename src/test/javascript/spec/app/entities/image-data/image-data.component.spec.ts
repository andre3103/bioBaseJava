import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { ImageDataComponent } from 'app/entities/image-data/image-data.component';
import { ImageDataService } from 'app/entities/image-data/image-data.service';
import { ImageData } from 'app/shared/model/image-data.model';

describe('Component Tests', () => {
  describe('ImageData Management Component', () => {
    let comp: ImageDataComponent;
    let fixture: ComponentFixture<ImageDataComponent>;
    let service: ImageDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [ImageDataComponent],
      })
        .overrideTemplate(ImageDataComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImageDataComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImageDataService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ImageData(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.imageData && comp.imageData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
