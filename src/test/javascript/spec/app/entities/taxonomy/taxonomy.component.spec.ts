import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { TaxonomyComponent } from 'app/entities/taxonomy/taxonomy.component';
import { TaxonomyService } from 'app/entities/taxonomy/taxonomy.service';
import { Taxonomy } from 'app/shared/model/taxonomy.model';

describe('Component Tests', () => {
  describe('Taxonomy Management Component', () => {
    let comp: TaxonomyComponent;
    let fixture: ComponentFixture<TaxonomyComponent>;
    let service: TaxonomyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [TaxonomyComponent],
      })
        .overrideTemplate(TaxonomyComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TaxonomyComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TaxonomyService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Taxonomy(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.taxonomies && comp.taxonomies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
