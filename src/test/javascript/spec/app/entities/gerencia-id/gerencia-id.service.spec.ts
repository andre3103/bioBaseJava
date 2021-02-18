import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GerenciaIdService } from 'app/entities/gerencia-id/gerencia-id.service';
import { IGerenciaId, GerenciaId } from 'app/shared/model/gerencia-id.model';

describe('Service Tests', () => {
  describe('GerenciaId Service', () => {
    let injector: TestBed;
    let service: GerenciaIdService;
    let httpMock: HttpTestingController;
    let elemDefault: IGerenciaId;
    let expectedResult: IGerenciaId | IGerenciaId[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GerenciaIdService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GerenciaId(0, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataGeracao: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a GerenciaId', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataGeracao: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataGeracao: currentDate,
          },
          returnedFromService
        );

        service.create(new GerenciaId()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GerenciaId', () => {
        const returnedFromService = Object.assign(
          {
            loginName: 'BBBBBB',
            dataGeracao: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataGeracao: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of GerenciaId', () => {
        const returnedFromService = Object.assign(
          {
            loginName: 'BBBBBB',
            dataGeracao: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataGeracao: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a GerenciaId', () => {
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
