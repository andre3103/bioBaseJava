import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITraceFile } from 'app/shared/model/trace-file.model';

type EntityResponseType = HttpResponse<ITraceFile>;
type EntityArrayResponseType = HttpResponse<ITraceFile[]>;

@Injectable({ providedIn: 'root' })
export class TraceFileService {
  public resourceUrl = SERVER_API_URL + 'api/trace-files';

  constructor(protected http: HttpClient) {}

  create(traceFile: ITraceFile): Observable<EntityResponseType> {
    return this.http.post<ITraceFile>(this.resourceUrl, traceFile, { observe: 'response' });
  }

  update(traceFile: ITraceFile): Observable<EntityResponseType> {
    return this.http.put<ITraceFile>(this.resourceUrl, traceFile, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITraceFile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITraceFile[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
