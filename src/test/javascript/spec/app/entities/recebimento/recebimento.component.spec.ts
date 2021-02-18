import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { RecebimentoComponent } from 'app/entities/recebimento/recebimento.component';
import { RecebimentoService } from 'app/entities/recebimento/recebimento.service';
import { Recebimento } from 'app/shared/model/recebimento.model';

describe('Component Tests', () => {
  describe('Recebimento Management Component', () => {
    let comp: RecebimentoComponent;
    let fixture: ComponentFixture<RecebimentoComponent>;
    let service: RecebimentoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [RecebimentoComponent],
      })
        .overrideTemplate(RecebimentoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RecebimentoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RecebimentoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Recebimento(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.recebimentos && comp.recebimentos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
