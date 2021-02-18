import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { SequenciamentoDetailComponent } from 'app/entities/sequenciamento/sequenciamento-detail.component';
import { Sequenciamento } from 'app/shared/model/sequenciamento.model';

describe('Component Tests', () => {
  describe('Sequenciamento Management Detail Component', () => {
    let comp: SequenciamentoDetailComponent;
    let fixture: ComponentFixture<SequenciamentoDetailComponent>;
    const route = ({ data: of({ sequenciamento: new Sequenciamento(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [SequenciamentoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SequenciamentoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SequenciamentoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sequenciamento on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sequenciamento).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
