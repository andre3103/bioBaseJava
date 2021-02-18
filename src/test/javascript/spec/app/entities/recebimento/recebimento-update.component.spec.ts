import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { RecebimentoUpdateComponent } from 'app/entities/recebimento/recebimento-update.component';
import { RecebimentoService } from 'app/entities/recebimento/recebimento.service';
import { Recebimento } from 'app/shared/model/recebimento.model';

describe('Component Tests', () => {
  describe('Recebimento Management Update Component', () => {
    let comp: RecebimentoUpdateComponent;
    let fixture: ComponentFixture<RecebimentoUpdateComponent>;
    let service: RecebimentoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [RecebimentoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RecebimentoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RecebimentoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RecebimentoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Recebimento(123);
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
        const entity = new Recebimento();
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
