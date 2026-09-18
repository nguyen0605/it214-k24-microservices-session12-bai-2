# Phân tích và Giải pháp Fallback Pattern cho Banner-Service

## Phần 1 – Phân tích quy tắc chữ ký phương thức Fallback
Để Resilience4j nhận diện và gọi đúng phương thức Fallback khi xảy ra lỗi hoặc circuit breaker mở, chữ ký (signature) của phương thức Fallback bắt buộc phải tuân thủ các quy tắc sau:
1. **Tên phương thức**: Có thể tùy đặt, nhưng phải được cấu hình chính xác trong thuộc tính `fallbackMethod` của annotation `@CircuitBreaker`.
2. **Kiểu trả về (Return type)**: Phải trùng khớp hoàn toàn với kiểu trả về của phương thức gốc (ví dụ: `String`, `ResponseEntity<List<Banner>>`, v.v.).
3. **Tham số (Parameters)**: Phải chứa toàn bộ các tham số của phương thức gốc theo đúng thứ tự, và có thể (hoặc bắt buộc phải có nếu muốn bắt ngoại lệ) bổ sung một tham số kiểu `Throwable` (hoặc `Exception`, `CallNotPermittedException`, v.v.) ở vị trí cuối cùng để hứng lỗi.

## Phần 2 – Thực thi mã nguồn
Phần mã nguồn minh họa việc áp dụng `@CircuitBreaker` kèm theo `fallbackMethod` và xử lý ngoại lệ `Throwable` được đặt trong các file source code đính kèm dự án.