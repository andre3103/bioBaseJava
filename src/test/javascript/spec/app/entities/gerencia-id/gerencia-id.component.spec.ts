import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { GerenciaIdComponent } from 'app/entities/gerencia-id/gerencia-id.component';
import { GerenciaIdService } from 'app/entities/gerencia-id/gerencia-id.service';
import { GerenciaId } from 'app/shared/model/gerencia-id.model';

describe('Component Tests', () => {
  describe('GerenciaId Management Component', () => {
    let comp: GerenciaIdComponent;
    let fixture: ComponentFixture<GerenciaIdComponent>;
    let service: GerenciaIdService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [GerenciaIdComponent],
      })
        .overrideTemplate(GerenciaIdComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GerenciaIdComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GerenciaIdService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GerenciaId(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.gerenciaIds && comp.gerenciaIds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
