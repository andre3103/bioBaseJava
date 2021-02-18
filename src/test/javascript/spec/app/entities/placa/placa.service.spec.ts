import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { PlacaService } from 'app/entities/placa/placa.service';
import { IPlaca, Placa } from 'app/shared/model/placa.model';

describe('Service Tests', () => {
  describe('Placa Service', () => {
    let injector: TestBed;
    let service: PlacaService;
    let httpMock: HttpTestingController;
    let elemDefault: IPlaca;
    let expectedResult: IPlaca | IPlaca[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PlacaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Placa(0, currentDate, 'AAAAAAA', 'AAAAAAA', currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataMontagem: currentDate.format(DATE_FORMAT),
            data: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Placa', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataMontagem: currentDate.format(DATE_FORMAT),
            data: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataMontagem: currentDate,
            data: currentDate,
          },
          returnedFromService
        );

        service.create(new Placa()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Placa', () => {
        const returnedFromService = Object.assign(
          {
            dataMontagem: currentDate.format(DATE_FORMAT),
            marcador: 'BBBBBB',
            sequencia: 'BBBBBB',
            data: currentDate.format(DATE_FORMAT),
            status: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataMontagem: currentDate,
            data: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Placa', () => {
        const returnedFromService = Object.assign(
          {
            dataMontagem: currentDate.format(DATE_FORMAT),
            marcador: 'BBBBBB',
            sequencia: 'BBBBBB',
            data: currentDate.format(DATE_FORMAT),
            status: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataMontagem: currentDate,
            data: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Placa', () => {
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
