import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { TaxonomyUpdateComponent } from 'app/entities/taxonomy/taxonomy-update.component';
import { TaxonomyService } from 'app/entities/taxonomy/taxonomy.service';
import { Taxonomy } from 'app/shared/model/taxonomy.model';

describe('Component Tests', () => {
  describe('Taxonomy Management Update Component', () => {
    let comp: TaxonomyUpdateComponent;
    let fixture: ComponentFixture<TaxonomyUpdateComponent>;
    let service: TaxonomyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [TaxonomyUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TaxonomyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TaxonomyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TaxonomyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Taxonomy(123);
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
        const entity = new Taxonomy();
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
