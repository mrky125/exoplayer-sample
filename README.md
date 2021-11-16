# exoplayer-sample
Sample app like YouTube.

| full mode | bottom sheet appeared | short mode |
| ---- | ------------ | ----- |
| <image src="https://user-images.githubusercontent.com/69252773/141932917-d57e620c-eb76-4d8e-b5f0-84a7ff75df1a.jpg" width="240x" /> | <image src="https://user-images.githubusercontent.com/69252773/141933342-bddf4f33-e90d-486f-ad52-601d32d20ce1.jpg" width="240x" /> | <image src="https://user-images.githubusercontent.com/69252773/141933354-d7bcc684-c342-4af6-b054-ddcb9051993b.jpg" width="240x" /> |


# Features
- PlayerView is fixedly top of layout (not scroll)
- ListView or RecycerView (something scroll items) is under the player
  - It has horizontal scroll
- Full screen mode, when button in the controller view is tapped or screen orientation changes
- Bottom sheet behavior like YouTube Playlist
  - Fotter fragment is fixedly bottom of layout, and has enter/exit transition animation
  - Playlist modal bottom sheet like YouTube
- Choose video type
  - Short (this supposes showing trial or billing user flow at the end of video) 
  - Full (Prevalent video features that have playlists like YouTube)

# References
### ExoPlayer
- https://exoplayer.dev/doc/reference/com/google/android/exoplayer2/ui/PlayerView.html

### Configration Changing, full screen
- https://developer.android.com/guide/topics/resources/runtime-changes?hl=ja
- https://stackoverflow.com/questions/22848222/toggling-fullscreen-orientation-like-youtube
- https://j3iiifn.hatenablog.com/entry/2020/08/17/000000
- https://qiita.com/kiito1000/items/46bd861c3c3dd3220366#%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E8%A8%AD%E5%AE%9A%E3%82%92%E5%8F%96%E5%BE%97%E3%81%99%E3%82%8B

### Playlist, auto transition, window index
- https://stackoverflow.com/questions/40284772/exoplayer-2-playlist-listener
- https://qiita.com/niusounds/items/5252df4af9754388aa35

### Bitrate changing
- https://exoplayer.dev/track-selection.html
- https://www.youtube.com/watch?v=r-HxwXbI-1g
- https://blog.socialcast.jp/01/post-661/

### View, ...etc
- https://stackoverflow.com/questions/40494623/android-imageview-169

### Fragment enter/exit transition
- https://qiita.com/verno3632/items/a97942a461204af4f421
