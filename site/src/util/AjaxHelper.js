export default class AjaxHelper {
  /**
   * Ajax request callback.
   *
   * @callback actionCallback
   * @param [object] param
   */

  /**
   * @param {React.Component} component - Component.
   * @param {actionCallback} action - A function that performs AJAX request.
   * @param {function(data)} onSuccess - Success request handler.
   * @param {function(reason)} [onFailure] - Failed request handler (not when canceled).
   */
  constructor(component, action, onSuccess, onFailure) {
    this._promise = null;
    this._action = action;

    this._updateComponent = () => {
      component.forceUpdate();
    };

    this._onSuccess = response => {
      this._promise = null;

      if (onSuccess) {
        onSuccess(response);
      } else {
        this._updateComponent();
      }
    };

    this._onFailure = reason => {
      this._promise = null;
      if (reason.isCanceled === true) {
        return;
      }

      if (onFailure) {
        onFailure(reason);
      } else {
        this._updateComponent();
      }
    };

    this.isLoading = this.isLoading.bind(this);
    this.doAction = this.doAction.bind(this);
    this.cancel = this.cancel.bind(this);
  }

  isLoading() {
    return this._promise !== null;
  }

  /**
   * Execute AJAX request.
   *
   * @param {*} [param] - Parameter to pass to action callback, passed in constructor.
   */
  doAction(param) {
    this.cancel();

    this._promise = this._action(param)
      .then(this._onSuccess)
      .catch(this._onFailure);

    this._updateComponent();
  }

  /**
   * Cancels AJAX request if it's incomplete.
   */
  cancel() {
    if (this._promise !== null) {
      this._promise.cancel();
      this._promise = null;

      this._updateComponent();
    }
  }
}
