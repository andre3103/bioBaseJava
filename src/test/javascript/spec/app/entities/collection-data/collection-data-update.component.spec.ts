import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { CollectionDataUpdateComponent } from 'app/entities/collection-data/collection-data-update.component';
import { CollectionDataService } from 'app/entities/collection-data/collection-data.service';
import { CollectionData } from 'app/shared/model/collection-data.model';

describe('Component Tests', () => {
  describe('CollectionData Management Update Component', () => {
    let comp: CollectionDataUpdateComponent;
    let fixture: ComponentFixture<CollectionDataUpdateComponent>;
    let service: CollectionDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [CollectionDataUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CollectionDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CollectionDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CollectionDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CollectionData(123);
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
        const entity = new CollectionData();
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
