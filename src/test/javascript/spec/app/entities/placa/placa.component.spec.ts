import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { PlacaComponent } from 'app/entities/placa/placa.component';
import { PlacaService } from 'app/entities/placa/placa.service';
import { Placa } from 'app/shared/model/placa.model';

describe('Component Tests', () => {
  describe('Placa Management Component', () => {
    let comp: PlacaComponent;
    let fixture: ComponentFixture<PlacaComponent>;
    let service: PlacaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [PlacaComponent],
      })
        .overrideTemplate(PlacaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PlacaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PlacaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Placa(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.placas && comp.placas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
