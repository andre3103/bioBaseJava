import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TaxonomyService } from 'app/entities/taxonomy/taxonomy.service';
import { ITaxonomy, Taxonomy } from 'app/shared/model/taxonomy.model';

describe('Service Tests', () => {
  describe('Taxonomy Service', () => {
    let injector: TestBed;
    let service: TaxonomyService;
    let httpMock: HttpTestingController;
    let elemDefault: ITaxonomy;
    let expectedResult: ITaxonomy | ITaxonomy[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TaxonomyService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Taxonomy(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Taxonomy', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Taxonomy()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Taxonomy', () => {
        const returnedFromService = Object.assign(
          {
            phylum: 'BBBBBB',
            tclass: 'BBBBBB',
            torder: 'BBBBBB',
            family: 'BBBBBB',
            subfamily: 'BBBBBB',
            genus: 'BBBBBB',
            specie: 'BBBBBB',
            identif: 'BBBBBB',
            identifierEmail: 'BBBBBB',
            identifierInstitution: 'BBBBBB',
            identificationMethod: 'BBBBBB',
            taxonomyNotes: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Taxonomy', () => {
        const returnedFromService = Object.assign(
          {
            phylum: 'BBBBBB',
            tclass: 'BBBBBB',
            torder: 'BBBBBB',
            family: 'BBBBBB',
            subfamily: 'BBBBBB',
            genus: 'BBBBBB',
            specie: 'BBBBBB',
            identif: 'BBBBBB',
            identifierEmail: 'BBBBBB',
            identifierInstitution: 'BBBBBB',
            identificationMethod: 'BBBBBB',
            taxonomyNotes: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Taxonomy', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
