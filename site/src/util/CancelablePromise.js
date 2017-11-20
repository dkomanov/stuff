// from https://reactjs.org/blog/2015/12/16/ismounted-antipattern.html
export default class CancelablePromise {
  constructor(promise) {
    this.hasCanceled = false;
    this.wrappedPromise = new Promise((resolve, reject) => {
      promise.then(
        val => this.hasCanceled ? reject({isCanceled: true, data: val}) : resolve(val),
        error => this.hasCanceled ? reject({isCanceled: true, data: error}) : reject(error)
      );
    });

    this.then = this.then.bind(this);
    this.catch = this.catch.bind(this);
    this.cancel = this.cancel.bind(this);
  }

  then(resolve, reject) {
    this.wrappedPromise = this.wrappedPromise.then(resolve, reject);
    return this;
  }

  catch(f) {
    this.wrappedPromise = this.wrappedPromise.catch(f);
    return this;
  }

  cancel() {
    this.hasCanceled = true;
  }
}
