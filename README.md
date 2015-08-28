Example of clustering with Android Maps Extension and async marker's icon load with Glide

### Async load marker's icon
```java
private void loadMarkerIcon(final Marker marker) {
    Glide.with(this).load("http://www.myiconfinder.com/uploads/iconsets/256-256-a5485b563efc4511e0cd8bd04ad0fe9e.png")
            .asBitmap().fitCenter().into(new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
            BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmap);
            marker.setIcon(icon);
        }
    });
}
```

### Create Cluster icon with text to the number or markers
```java
public ClusterOptions getClusterOptions(List<Marker> list) {
    Bitmap bitmap = base.copy(Bitmap.Config.ARGB_8888, true);
    Rect bounds = new Rect();
    String text = String.valueOf(list.size());
    paint.getTextBounds(text, 0, text.length(), bounds);
    float x = bitmap.getWidth() / 2.0f;
    float y = (bitmap.getHeight() - bounds.height()) / 2.0f - bounds.top;
    Canvas canvas = new Canvas(bitmap);
    canvas.drawText(text, x, y, paint);
    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmap);
    return new ClusterOptions().anchor(0.5f, 0.5f).icon(icon);
}
```
