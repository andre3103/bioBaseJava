import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { ImageDataUpdateComponent } from 'app/entities/image-data/image-data-update.component';
import { ImageDataService } from 'app/entities/image-data/image-data.service';
import { ImageData } from 'app/shared/model/image-data.model';

describe('Component Tests', () => {
  describe('ImageData Management Update Component', () => {
    let comp: ImageDataUpdateComponent;
    let fixture: ComponentFixture<ImageDataUpdateComponent>;
    let service: ImageDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [ImageDataUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ImageDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImageDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImageDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ImageData(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ImageData();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
