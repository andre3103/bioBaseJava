import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { EntregaComponent } from 'app/entities/entrega/entrega.component';
import { EntregaService } from 'app/entities/entrega/entrega.service';
import { Entrega } from 'app/shared/model/entrega.model';

describe('Component Tests', () => {
  describe('Entrega Management Component', () => {
    let comp: EntregaComponent;
    let fixture: ComponentFixture<EntregaComponent>;
    let service: EntregaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [EntregaComponent],
      })
        .overrideTemplate(EntregaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntregaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntregaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Entrega(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entregas && comp.entregas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
