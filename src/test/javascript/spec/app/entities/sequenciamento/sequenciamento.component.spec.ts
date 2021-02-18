import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { SequenciamentoComponent } from 'app/entities/sequenciamento/sequenciamento.component';
import { SequenciamentoService } from 'app/entities/sequenciamento/sequenciamento.service';
import { Sequenciamento } from 'app/shared/model/sequenciamento.model';

describe('Component Tests', () => {
  describe('Sequenciamento Management Component', () => {
    let comp: SequenciamentoComponent;
    let fixture: ComponentFixture<SequenciamentoComponent>;
    let service: SequenciamentoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [SequenciamentoComponent],
      })
        .overrideTemplate(SequenciamentoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SequenciamentoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SequenciamentoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Sequenciamento(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sequenciamentos && comp.sequenciamentos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
