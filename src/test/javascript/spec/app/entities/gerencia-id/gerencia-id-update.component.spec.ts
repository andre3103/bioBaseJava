import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { GerenciaIdUpdateComponent } from 'app/entities/gerencia-id/gerencia-id-update.component';
import { GerenciaIdService } from 'app/entities/gerencia-id/gerencia-id.service';
import { GerenciaId } from 'app/shared/model/gerencia-id.model';

describe('Component Tests', () => {
  describe('GerenciaId Management Update Component', () => {
    let comp: GerenciaIdUpdateComponent;
    let fixture: ComponentFixture<GerenciaIdUpdateComponent>;
    let service: GerenciaIdService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [GerenciaIdUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GerenciaIdUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GerenciaIdUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GerenciaIdService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GerenciaId(123);
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
        const entity = new GerenciaId();
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
