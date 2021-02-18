import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { GerenciaIdDetailComponent } from 'app/entities/gerencia-id/gerencia-id-detail.component';
import { GerenciaId } from 'app/shared/model/gerencia-id.model';

describe('Component Tests', () => {
  describe('GerenciaId Management Detail Component', () => {
    let comp: GerenciaIdDetailComponent;
    let fixture: ComponentFixture<GerenciaIdDetailComponent>;
    const route = ({ data: of({ gerenciaId: new GerenciaId(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [GerenciaIdDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GerenciaIdDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GerenciaIdDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gerenciaId on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gerenciaId).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
