# SongToNotes
Sound to Piano notes but piano notes sound too artificial.
There are two reasons for this:
  * Application presses the notes constantly every two seconds.
  * Application is trying to make the sound chart just like a note. So it doesn't press two notes at the same time.

FFT can be used to solve these problems. FFT can express sounds in frequencies. Piano notes are already produced directly in frequency.
