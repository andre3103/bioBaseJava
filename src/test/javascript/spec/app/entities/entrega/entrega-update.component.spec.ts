import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { EntregaUpdateComponent } from 'app/entities/entrega/entrega-update.component';
import { EntregaService } from 'app/entities/entrega/entrega.service';
import { Entrega } from 'app/shared/model/entrega.model';

describe('Component Tests', () => {
  describe('Entrega Management Update Component', () => {
    let comp: EntregaUpdateComponent;
    let fixture: ComponentFixture<EntregaUpdateComponent>;
    let service: EntregaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [EntregaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EntregaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntregaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntregaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Entrega(123);
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
        const entity = new Entrega();
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
