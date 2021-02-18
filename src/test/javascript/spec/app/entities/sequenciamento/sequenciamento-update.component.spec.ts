import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { SequenciamentoUpdateComponent } from 'app/entities/sequenciamento/sequenciamento-update.component';
import { SequenciamentoService } from 'app/entities/sequenciamento/sequenciamento.service';
import { Sequenciamento } from 'app/shared/model/sequenciamento.model';

describe('Component Tests', () => {
  describe('Sequenciamento Management Update Component', () => {
    let comp: SequenciamentoUpdateComponent;
    let fixture: ComponentFixture<SequenciamentoUpdateComponent>;
    let service: SequenciamentoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [SequenciamentoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SequenciamentoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SequenciamentoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SequenciamentoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Sequenciamento(123);
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
        const entity = new Sequenciamento();
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
