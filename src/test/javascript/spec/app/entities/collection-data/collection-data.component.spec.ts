import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { CollectionDataComponent } from 'app/entities/collection-data/collection-data.component';
import { CollectionDataService } from 'app/entities/collection-data/collection-data.service';
import { CollectionData } from 'app/shared/model/collection-data.model';

describe('Component Tests', () => {
  describe('CollectionData Management Component', () => {
    let comp: CollectionDataComponent;
    let fixture: ComponentFixture<CollectionDataComponent>;
    let service: CollectionDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [CollectionDataComponent],
      })
        .overrideTemplate(CollectionDataComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CollectionDataComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CollectionDataService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CollectionData(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.collectionData && comp.collectionData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
