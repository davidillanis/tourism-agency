import { Injectable } from '@angular/core';
import Swal, { SweetAlertPosition } from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor() { }

  /**
   * alert success
   * @param title alert title
   * @param text body of alert
   * @param buttonEnable control if visible the button
   * @param timer time of live of the alert
   */
  async alertSuccess(title: string, text: string, buttonEnable: boolean = true, timer: number = 1500, updatePage: boolean = false): Promise<void> {
    await Swal.fire({
      icon: "success",
      title: title,
      text: text,
      showConfirmButton: buttonEnable,
      timer: timer
    });
    if (updatePage) {
      location.reload();
    }
  }
  /**
   * method for notification alerts
   * @param text text of alert info,
   * @param time alert duration time
   * @param colorText color of text
   * @param backgroundColor backgound color
   * @param postition this postition in web
   */
  async alertInfo(text: string, time: number = 1200, colorText: string = "#229954", backgroundColor:string="default", postition: SweetAlertPosition = "bottom-end"): Promise<void> {
    await Swal.fire({
      toast: true,
      position: postition,
      color: colorText,
      showConfirmButton: false,
      timer: time,
      background: backgroundColor,
      customClass: {
        popup: 'small-toast',
        icon: 'small-icon',
      },
      html: `
        <div class="small-toast-content">
          <h6>`
        + text +
        `<\h6>
        </div>
      `
    });
  }

  /**
   * alert Error
   * @param title alert title
   * @param body body of the alert error
   * @param footer footer, accept HTML
   */
  alertError(title: string, body: string, footer: string = '<a href="#">Why do I have this issue?</a>'): void {
    Swal.fire({
      icon: "error",
      title: title,
      text: body,
      footer: footer
    });
  }
}
