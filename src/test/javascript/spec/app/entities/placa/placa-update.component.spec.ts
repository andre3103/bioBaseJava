import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { PlacaUpdateComponent } from 'app/entities/placa/placa-update.component';
import { PlacaService } from 'app/entities/placa/placa.service';
import { Placa } from 'app/shared/model/placa.model';

describe('Component Tests', () => {
  describe('Placa Management Update Component', () => {
    let comp: PlacaUpdateComponent;
    let fixture: ComponentFixture<PlacaUpdateComponent>;
    let service: PlacaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [PlacaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PlacaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PlacaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PlacaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Placa(123);
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
        const entity = new Placa();
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
