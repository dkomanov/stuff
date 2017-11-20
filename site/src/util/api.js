import axios from 'axios';
import {CancelablePromise} from '.';

export function loadJson(path) {
  return new CancelablePromise(axios.get(path));
}
